package Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultsetHandler <TReturnValue> {
    public TReturnValue handle(ResultSet rs) throws SQLException;
}
