package banco.utils;

public enum PaginaWeb {

    EVALART("https://tasks.evalartapp.com/automatization/");

    private String url;

    PaginaWeb(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }
}