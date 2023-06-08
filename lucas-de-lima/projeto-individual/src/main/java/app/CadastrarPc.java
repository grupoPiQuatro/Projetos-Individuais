/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import connection.ConnectionAzure;
import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import models.Componente;
import models.Computador;
import models.Localizacao;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author faculdade
 */
public class CadastrarPc {

    public void verificarPc() throws IOException {
        InserirMetrica im = new InserirMetrica();
        connection.ConnectionAzure conexao = new ConnectionAzure();

        JdbcTemplate con = conexao.getConnection();

        InfoPc infoPc = new InfoPc();

        String so = infoPc.sistemaOperacional();

        String hostName = infoPc.hostName();

        List<Computador> listaComputador = con.query("select hostname,"
                + " sistemaOperacional, status from Computador where hostname = ?;",
                new BeanPropertyRowMapper(Computador.class), hostName);

        Integer computadorEncontrado = listaComputador.size();

        if (computadorEncontrado > 0) {
            System.out.println("Computador já cadastrado !!");
            System.out.println("Inserindo dados:");
            new Timer().scheduleAtFixedRate(new TimerTask() {

                public void run() {
                    im.inserirMetrica();
                }
            }, 0, 10000);
        } else {
            System.out.println("Realizando o cadastro do PC");
            cadastrarPc();
        }

    }

    public void cadastrarPc() throws IOException {
        InserirMetrica im = new InserirMetrica();
        ConnectionAzure conexao = new ConnectionAzure();

        JdbcTemplate con = conexao.getConnection();

        InfoPc infoPc = new InfoPc();

        String hostName = infoPc.hostName();

        String mac = infoPc.mac();

        String so = infoPc.sistemaOperacional();

        Double freqCpu = infoPc.frequenciaCpu();
        Double qtdRam = infoPc.qtdRam();
        Double qtdArmazenamento = infoPc.qtdArmazenamento();
        Integer tipoDisco = 4;
        Double redeMs = 1.0;
        String status = "Operando";
        String fkEmpresa = "1";
        String discoTipo = "ssd";

        List<Componente> componentes = con.query("select * from Componente;",
                new BeanPropertyRowMapper(Componente.class));

        Boolean validarRede = false;
        Boolean validarRam = false;
        Boolean validarDiscoSSD = false;
        Boolean validarDiscoHD = false;
        Boolean validarCpu = false;

        for (Componente comp : componentes) {
            if (comp.getFkTipo() == 1) {
                if (comp.getNumeroChave().equals(redeMs)) {
                    validarRede = true;
                }
            }

            if (comp.getFkTipo() == 3) {
                if (comp.getNumeroChave().equals(freqCpu)) {
                    validarCpu = true;
                }
            }

            if (comp.getFkTipo() == 2) {
                if (comp.getNumeroChave().equals(qtdRam)) {
                    validarRam = true;
                }
            }

            if (discoTipo.equalsIgnoreCase("ssd")) {
                if (comp.getFkTipo() == 4) {
                    if (comp.getNumeroChave().equals(qtdArmazenamento)) {
                        validarDiscoSSD = true;
                    }
                }
            } else {
                if (comp.getFkTipo() == 5) {
                    if (comp.getNumeroChave().equals(qtdArmazenamento)) {
                        validarDiscoHD = true;
                    }
                }
            }

        }

        if (validarRede == false) {
            int linhaComponenteCpu = con.update("insert into Componente (numeroChave, unidadeMedida, fkTipo) values (?, ?, ?)",
                    redeMs,
                    "ms",
                    1
            );
        }

        if (validarCpu == false) {
            int linhaComponenteCpu = con.update("insert into Componente (numeroChave, unidadeMedida, fkTipo) values (?, ?, ?)",
                    freqCpu,
                    "hz",
                    3
            );
        }

        if (validarRam == false) {
            int linhaComponenteRam = con.update("insert into Componente (numeroChave, unidadeMedida, fkTipo) values (?, ?, ?)",
                    qtdRam,
                    "gb",
                    2
            );

        }

        if (discoTipo.equalsIgnoreCase("ssd")) {
            if (validarDiscoSSD == false) {
                int linhaComponenteDisco = con.update("insert into Componente (numeroChave, unidadeMedida, fkTipo) values (?, ?, ?)",
                        qtdArmazenamento,
                        "gb",
                        tipoDisco
                );

            }
        } else {
            if (validarDiscoHD == false) {
                int linhaComponenteDisco = con.update("insert into Componente (numeroChave, unidadeMedida, fkTipo) values (?, ?, ?)",
                        qtdArmazenamento,
                        "gb",
                        tipoDisco
                );
            }

        }

        String setor = "Setor do Andres";

        int linhaLocalizacao = con.update("insert into Localizacao (setor) values (?)",
                setor
        );

        List<Localizacao> loc = con.query("select idLocalizacao from Localizacao order by idLocalizacao desc",
                new BeanPropertyRowMapper(Localizacao.class));

        Integer fkLocalizacao = null;

        for (int i = 0; i < loc.size(); i++) {
            if (i == 0) {
                fkLocalizacao = loc.get(i).getIdLocalizacao();
            }
        }

        int linhasInseridas = con.update("insert into Computador values (?, ?, ?, ?, ?, ?)",
                hostName,
                status,
                so,
                mac,
                fkLocalizacao,
                fkEmpresa
        );

        Integer idRede = 0;
        for (Componente comp : componentes) {
            if (comp.getFkTipo() == 1) {
                if (comp.getNumeroChave().equals(redeMs)) {
                    idRede = comp.getIdComponente();

                }
            }
        }

        Integer idRam = 0;
        for (Componente comp : componentes) {
            if (comp.getFkTipo() == 2) {
                if (comp.getNumeroChave().equals(qtdRam)) {
                    idRam = comp.getIdComponente();
                }
            }
        }

        Integer idCpu = 1;
        for (Componente comp : componentes) {
            if (comp.getFkTipo() == 3) {
                if (comp.getNumeroChave().equals(freqCpu)) {
                    idCpu = comp.getIdComponente();
                }
            }
        }
        Integer idArmazenamento = 0;
        for (Componente comp : componentes) {
            if ((comp.getFkTipo() == 4) || (comp.getFkTipo() == 5)) {
                if (comp.getNumeroChave().equals(qtdArmazenamento)) {
                    idArmazenamento = comp.getIdComponente();
                }
            }
        }

        //AZURE
        int associarRede = con.update("insert into Config (fkComputador, fkComponente) values (?,?)", hostName, idRede);
        int associarRam = con.update("insert into Config (fkComputador, fkComponente) values (?,?)", hostName, idRam);
        int associarCpu = con.update("insert into Config (fkComputador, fkComponente) values (?,?)", hostName, idCpu);
        int associarArmazenamento = con.update("insert into Config (fkComputador, fkComponente) values (?,?)", hostName, idArmazenamento);
        System.out.println("PC cadastrado !!");
        verificarPc();
    }
}
