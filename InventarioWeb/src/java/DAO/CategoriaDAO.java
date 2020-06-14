
package DAO;

import Model.Categoria;
import java.util.List;
/**
 *
 * @author ruben
 */
public interface CategoriaDAO {
    
    public List<Categoria> Listar();
    public List<Categoria> Listar2();
    public Categoria editarCat(int id_cat_edit);
    public Boolean guardarCat(Categoria categoria);
    public Boolean borrarCat(int id_cat_borrar);
    
    
}
