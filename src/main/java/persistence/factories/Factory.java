package persistence.factories;

import java.sql.SQLException;

public interface Factory<T> {
	
	public T create() throws ClassNotFoundException, SQLException;
}
