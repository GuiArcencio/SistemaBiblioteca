package app.Autor;

import java.util.List;


public interface AutorService {
    public void addAutor (Autor a);

    public List<Autor> getAutores ();
    public Autor getAutor (String name);

    public Autor editAutor (Autor a);
    
    public void deleteAutor (String name);

    public boolean autorExiste (String name);
}
