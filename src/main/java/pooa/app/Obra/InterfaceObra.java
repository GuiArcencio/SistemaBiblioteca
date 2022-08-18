package main.java.pooa.Interface;

public interface InterfaceObra {
    public void adicionarAutor(String autor);
    public void removerAutor(int indice);
    public void adicionarPalavraChave(String palavra);
    public void removerPalavraChave(int indice);
    public void adicionarCopia(int idCopia);
    public void removerCopia(int indice);
    public void marcarEmprestadoCopia(int idCopia);
    public void marcarDevolverCopia(int idCopia);
    public void marcarDisponivelCopia(int idCopia);

}
