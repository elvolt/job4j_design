package ru.job4j.lsp.ex3;

/*
    Было одно хранилище, затем создали другое, но подумали, что не
    нужно хранить пустые строки.
    Нарушение LSP - усиление предусловия.
 */

public class AnotherStore extends Store {
    @Override
    public void save(String str) {
        if (str.length() == 0) {
            throw new IllegalArgumentException("Too short");
        }
        super.save(str);
    }
}
