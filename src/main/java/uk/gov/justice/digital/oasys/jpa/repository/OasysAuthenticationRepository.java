package uk.gov.justice.digital.oasys.jpa.repository;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.Optional;

@Repository
public class OasysAuthenticationRepository {

    @PersistenceContext
    private EntityManager em;

    public Optional<String> validateCredentials(String username, String password) {
        Session session = em.unwrap( Session.class );

        // The function indirectly updates the database and so must be called within an anonymous block
        // We therefore have to call the function using the raw PL SQL
        //TODO Remove logging to CLOG once this is working in all environments
        String query = "DECLARE " +
                        "    LV_RES VARCHAR2(4000); " +
                        "BEGIN " +
                        "    LV_RES := RESTFUL_API_PKG.USER_INFO(P_USER => ?, P_PASSWORD => ?); " +
                        "    ? := LV_RES; " +
                        "    CLOG_API.INS(P_SOURCE=>'TESTING:RESTFUL_API.USER_LOGIN', P_TEXT => LV_RES, P_LEVEL => 5); " +
                        "END;";

        String result = session.doReturningWork(
                connection -> {
                    try (CallableStatement function = connection
                            .prepareCall(
                                    query )) {
                        function.setString(1,username);
                        function.setString(2,password);
                        function.registerOutParameter( 3, Types.VARCHAR );
                        function.execute();
                        return function.getString( 3);
                    }
                } );

        return Optional.ofNullable(result);
    }
}
