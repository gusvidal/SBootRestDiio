package cl.guti.service;

import java.util.List;
import cl.guti.model.Diio;

public interface IDiioService {
    public List<Diio> listar();

	public Diio listarId(int id);

	public int save(Diio p);

	public void delete(int id);
}
