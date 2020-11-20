package br.com.deal.config.validacao;

public class ErroDeFormularioDto {
    private final String campo;
    private final String erro;

    public ErroDeFormularioDto(String campo, String erro) {
        this.campo = campo;
        this.erro = erro;
    }

    public String getCampo() {
        return campo;
    }

    public String getErro() {
        return erro;
    }
}