package br.com.roberto.hateos;

import br.com.roberto.controller.dto.UsuarioDTO;
import br.com.roberto.model.Usuario;

import javax.ws.rs.core.UriInfo;

/**
 *  @criado em: 14/04/2020 - {22:12}
 *  @projeto  : cdiexample
 *  @autor    : roberto
 */


public class MensagensHateos {
    public UsuarioDTO acoesHateosParaUsuario(UriInfo uriInfo, UsuarioDTO usuario) {

        usuario.addLink(this.getUriForSelf(uriInfo, usuario), "listarPorId");
        usuario.addLink(this.getUriAdicionarTodos(uriInfo), "adicionar");
        usuario.addLink(this.getUriForExcluir(uriInfo, usuario), "excluir");
        usuario.addLink(this.getUriForAlterar(uriInfo, usuario), "alterar");
        usuario.addLink(this.getUriForListarTodos(uriInfo), "listarTodos", "Atenção a Paginação");
        return usuario;
    }

    public UsuarioDTO acoesHateosParaEdicaoUsuario(UriInfo uriInfo, UsuarioDTO usuario) {

        usuario.addLink(this.getUriForSelfEdicao(uriInfo, usuario), "listarPorId");
        usuario.addLink(this.getUriForAdicionarEdicao(uriInfo), "adicionar");
        usuario.addLink(this.getUriForExcluirEdicao(uriInfo, usuario), "excluir");
        usuario.addLink(this.getUriForAlterarEdicao(uriInfo, usuario), "alterar");
        usuario.addLink(this.getUriForListarTodos(uriInfo), "listarTodos", "Atenção a Paginação");
        return usuario;
    }

    public UsuarioDTO acoesHateosParaInsercao(UriInfo uriInfo, UsuarioDTO usuario) {

        usuario.addLink(this.getUriForSelfInsercao(uriInfo, usuario), "listarPorId");
        usuario.addLink(this.getUriForAdicionarInsercao(uriInfo), "adicionar");
        usuario.addLink(this.getUriForExcluirInsercao(uriInfo, usuario), "excluir");
        usuario.addLink(this.getUriForAlterarInsercao(uriInfo, usuario), "alterar");
        usuario.addLink(this.getUriForListarTodos(uriInfo), "listarTodos", "Atenção a Paginação");
        return usuario;
    }

    private String getUriForAlterarInsercao(UriInfo uriInfo, UsuarioDTO usuario) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()+usuario.getId()).build()
                .toString();
        return uri;
    }

    private String getUriForExcluirInsercao(UriInfo uriInfo, UsuarioDTO usuario) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()+usuario.getId()).build()
                .toString();
        return uri;
    }

    private String getUriForAdicionarInsercao(UriInfo uriInfo) {
        int tamanho = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build().toString().length();
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build().toString().substring(0,tamanho-1);
        return uri;

    }

    private String getUriForSelfInsercao(UriInfo uriInfo, UsuarioDTO usuario) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()+usuario.getId()).build()
                .toString();

        return uri;
    }




    private String getUriForAlterarEdicao(UriInfo uriInfo, UsuarioDTO usuario) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build()
                .toString();
        return uri;
    }

    private String getUriForExcluirEdicao(UriInfo uriInfo, UsuarioDTO usuario) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build()
                .toString();
        return uri;
    }

    private String getUriForAdicionarEdicao(UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build().toString();
        int indice = uri.lastIndexOf("/");
        uri = uri.substring(0,indice);

        return uri;
    }

    private String getUriForSelfEdicao(UriInfo uriInfo, UsuarioDTO usuario) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build()
                .toString();

        return uri;
    }

    public UsuarioDTO acoesHateosParaExcluir(UriInfo uriInfo, UsuarioDTO usuario) {
        usuario.addLink(this.getUriForAdicionar(uriInfo), "adicionar");
        usuario.addLink(this.getUriForListarTodos(uriInfo), "listarTodos", "Atenção a Paginação");
        return usuario;
    }



    private String getUriForSelf(UriInfo uriInfo, UsuarioDTO usuario) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()+"/"+usuario.getId()).build()
                .toString();

        return uri;
    }

    private String getUriAdicionarTodos(UriInfo uriInfo) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build().toString();
        return uri;
    }

    private String getUriForAdicionar(UriInfo uriInfo) {

        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build().toString();
        int indice = uri.lastIndexOf("/");
        uri = uri.substring(0,indice);

        return uri;
    }

    private String getUriForExcluir(UriInfo uriInfo, UsuarioDTO usuario) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()+"/"+usuario.getId()).build()
                .toString();
        return uri;
    }

    private String getUriForAlterar(UriInfo uriInfo, UsuarioDTO usuario) {
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()+"/"+usuario.getId()).build()
                .toString();
        return uri;
    }

    private String getUriForListarTodos(UriInfo uriInfo) {
        int ultimoIndice = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build().toString().lastIndexOf("/");
        String texto;

        if (ultimoIndice>0) {
            texto = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build().toString();
            texto = texto.substring(0,ultimoIndice);
            return texto+ "?inicio=0&tamanho=10";
        }
        String uri = uriInfo.getBaseUriBuilder().path(uriInfo.getPath()).build().toString()+ "?inicio=0&tamanho=10";
        return uri;
    }
}
