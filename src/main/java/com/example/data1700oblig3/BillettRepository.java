package com.example.data1700oblig3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BillettRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
     class BillettRowmapper implements RowMapper < Billett > {
         //intellij copilot skrev denne koden. er ikke sikker på om jeg må sitere den?
         @Override
        public Billett mapRow(ResultSet rs, int rowNum) throws SQLException {
            Billett billett = new Billett();

            billett.setId(rs.getInt("id"));
            billett.setAntall(rs.getString("antall"));
            billett.setfValg(rs.getString("fValg"));
            billett.setfNavn(rs.getString("fNavn"));
            billett.seteNavn(rs.getString("eNavn"));
            billett.setTlfNr(rs.getString("tlfNr"));
            billett.setEpost(rs.getString("epost"));
            return billett;
        }
    }

    public List<Billett> alleBilletter(){
        return jdbcTemplate.query("select * from BILLETTER", new BillettRowmapper());
    }

    public int nyBillett(Billett billett){
        String sql = "insert into BILLETTER (FVALG, ANTALL, FNAVN, ENAVN, TLFNR, EPOST) values(?,?,?,?,?,?)";

        return jdbcTemplate.update(sql,
                billett.getfValg(),
                billett.getAntall(),
                billett.getfNavn(),
                billett.geteNavn(),
                billett.getTlfNr(),
                billett.getEpost());
    }

    public int oppdaterBillett(Billett billett){
        String sql = "update BILLETTER set fvalg = ?, antall = ?, fNavn = ?, eNavn = ?, tlfNr = ?, epost=?";
        return jdbcTemplate.update(sql,
                                    billett.getfValg(),
                                    billett.getAntall(),
                                    billett.getfNavn(),
                                    billett.geteNavn(),
                                    billett.getTlfNr(),
                                    billett.getEpost());
    }

    public int slettById(int id){
        String sql = "delete from BILLETTER where Id = ?";
        return jdbcTemplate.update(sql, new Object[]{
                id
        });
    }

    public Billett søkPåId(int id){
        return jdbcTemplate.queryForObject("select * from BILLETTER where id=?", new BillettRowmapper(), id);
    }
}

