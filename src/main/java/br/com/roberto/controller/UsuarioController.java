package br.com.roberto.controller;

import br.com.roberto.controller.bean.UsuarioFilterBean;
import br.com.roberto.hateos.MensagensHateos;
import br.com.roberto.model.UsuarioModel;
import br.com.roberto.negocio.UsuarioNegocio;
import io.swagger.annotations.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *  @criado em: 14/04/2020 - {20:50}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */

@Path("/usuarios")
@Produces(MediaType.APPLICATION_JSON )
@Consumes(MediaType.APPLICATION_JSON )
@Api(tags = {"v1"})
public class UsuarioController {

	@Inject
	private UsuarioNegocio usuarioNegocio;

	private MensagensHateos mensagensHateos;

	@GET
	@ApiOperation(value = "Recupera todos os usuarios")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 204, message = "Sucesso")
	})
	public Response listarUsuarios(@BeanParam UsuarioFilterBean filterBean, @Context UriInfo uriInfo) {
		List<UsuarioModel> usuarios = usuarioNegocio.listarUsuarios(filterBean.getInicio(), filterBean.getTamanho());

		List<UsuarioModel> todosUsuariosComHateos = new ArrayList<>();
		mensagensHateos = new MensagensHateos();

		usuarios.forEach(usuarioInterno ->{
			mensagensHateos.acoesHateosParaUsuario(uriInfo,usuarioInterno);
			todosUsuariosComHateos.add(usuarioInterno);
		});

		return Response
				.status(Status.OK)
				.entity(todosUsuariosComHateos)
				.build();
	}

	@GET
	@Path("/{id}")
	@ApiOperation(value = "Recupera um Usuário Por ID")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 204, message = "Sucesso")
	})
	public Response getUsuario(@ApiParam(required = true)
							   @PathParam("id") Long id, @Context UriInfo uriInfo) {

			mensagensHateos = new MensagensHateos();
			UsuarioModel usuario = usuarioNegocio.listaPorId(id);
			mensagensHateos.acoesHateosParaEdicaoUsuario(uriInfo,usuario);

			return Response
					.ok(usuario)
					.build();
	}

	@POST
	@ApiOperation(value = "Insere um Usuário")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 204, message = "Sucesso")
	})
	public Response adicionarUsuario(@ApiParam(required = true) UsuarioModel usuario, @Context UriInfo uriInfo) {

		mensagensHateos = new MensagensHateos();
		UsuarioModel novoUsuario = usuarioNegocio.adiciona(usuario);
		mensagensHateos.acoesHateosParaInsercao(uriInfo,novoUsuario);

		return Response
				.status(Status.CREATED)
				.entity(novoUsuario)
				.build();
	}

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "Atualiza um Usuário")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "Sucesso")
	})
	public Response atualizarUsuario(@ApiParam(required = true) @PathParam("id") Long id,  UsuarioModel usuario, @Context UriInfo uriInfo){

		mensagensHateos = new MensagensHateos();
		UsuarioModel usuarioAtualizado = usuarioNegocio.atualiza(id, usuario);
		mensagensHateos.acoesHateosParaEdicaoUsuario(uriInfo,usuarioAtualizado);

		return Response
				.status(Status.OK)
				.entity(usuarioAtualizado)
				.build();
	}

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "Exclui um Usuário")
	@ApiResponses({
			@ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 204, message = "Sucesso")
	})
	public Response excluirUsuario(@ApiParam(required = true) @PathParam("id") Long id, @Context UriInfo uriInfo) {
		boolean foiRemovido = usuarioNegocio.remove(id);

		if (foiRemovido){
			mensagensHateos = new MensagensHateos();
			UsuarioModel usuario = mensagensHateos.acoesHateosParaExcluir(uriInfo, new UsuarioModel());
			return Response
					.status(Status.OK)
					.entity(usuario)
					.build();
		}
		return null;
	}

}
