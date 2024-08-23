import DAO.*;
import Entity.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) {

    try{
        // Criar instâncias dos DAOs
        ProjetoDAO projetoDAO = new ProjetoDAO();
        EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
        OperarioDAO operarioDAO = new OperarioDAO();
        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        MaterialDAO materialDAO = new MaterialDAO();


        // Inserir projetos

        Projeto projeto1 = new Projeto();
        projeto1.setIdProjeto(1);
        projeto1.setNomeProjeto("Construção de Apt");
        projeto1.setLocal("Centro");
        projeto1.setDataIncio(Date.valueOf("2024-01-01"));
        projeto1.setDataTerminio(Date.valueOf("2024-12-31"));
        projetoDAO.inserirProjeto(projeto1);

        Projeto projeto2 = new Projeto();
        projeto2.setIdProjeto(2);
        projeto2.setNomeProjeto("Reforma do terminal Central ");
        projeto2.setLocal("Zona Norte");
        projeto2.setDataIncio(Date.valueOf("2023-02-01"));
        projeto2.setDataTerminio(Date.valueOf("2024-11-30"));
       projetoDAO.inserirProjeto(projeto2);

        // Inserir engenheiros
        Engenheiro engenheiro1 = new Engenheiro();
        engenheiro1.setIdEngenheiro(1);
        engenheiro1.setNomeEngenheiro("Lucas");
        engenheiro1.setEspecialidade("Estruturas");
        engenheiroDAO.inserirEngenheiro(engenheiro1);

        Engenheiro engenheiro2 = new Engenheiro();
        engenheiro2.setIdEngenheiro(2);
        engenheiro2.setNomeEngenheiro("Valdemir");
        engenheiro2.setEspecialidade("Eletrico");
        engenheiroDAO.inserirEngenheiro(engenheiro2);

        // Inserir operários
        Operario operario1 = new Operario();
        operario1.setIdOperario(1);
        operario1.setNomeOperario("Rodrigo");
        operario1.setFuncao("Pedreiro");
        operarioDAO.inserirOperario(operario1);

        Operario operario2 = new Operario();
        operario2.setIdOperario(2);
        operario2.setNomeOperario("Ismael");
        operario2.setFuncao("Aux. Construção");
        operarioDAO.inserirOperario(operario2);

        // Inserir equipamentos
        Equipamento equipamento1 = new Equipamento();
        equipamento1.setIdEquipamento(1);
        equipamento1.setNomeEquipamento("Betoneira");
        equipamento1.setTipo("Misturador");
        equipamentoDAO.inserirEquipamento(equipamento1);

        Equipamento equipamento2 = new Equipamento();
        equipamento2.setIdEquipamento(2);
        equipamento2.setNomeEquipamento("Andaime");
        equipamento2.setTipo("Apoio");
        equipamentoDAO.inserirEquipamento(equipamento2);

        // Inserir materiais
        Material material1 = new Material();
        material1.setIdMaterial(1);
        material1.setNomeMaterial("Cimento");
        material1.setQuantidade(500);
        materialDAO.inserirMaterial(material1);

        Material material2 = new Material();
        material2.setIdMaterial(2);
        material2.setNomeMaterial("Cal");
        material2.setQuantidade(300);
        materialDAO.inserirMaterial(material2);

        // Atualizar
        projeto1.setLocal("SP");
        projetoDAO.atualizarProjeto(projeto1);

        engenheiro1.setNomeEngenheiro("Lucas");
        engenheiroDAO.atualizarEngenheiro(engenheiro1);

        operario1.setFuncao("Marceneiro");
        operarioDAO.atualizarOperario(operario1);

        equipamento1.setNomeEquipamento("Trena");
        equipamento1.setTipo("Medida");
        equipamentoDAO.atualizarEquipamento(equipamento1);

        material1.setQuantidade(5);
        materialDAO.atualizarMaterial(material1);



        // Alocar , usar e consumir
        projetoDAO.alocarEngenheiro(projeto1.getIdProjeto(), engenheiro1.getIdEngenheiro());
        projetoDAO.alocarOperario(projeto1.getIdProjeto(),operario1.getIdOperario());
        projetoDAO.consumirMaterial(projeto1.getIdProjeto(), material1.getIdMaterial());
        projetoDAO.usarEquipamento(projeto1.getIdProjeto(), equipamento1.getIdEquipamento());
        projetoDAO.alocarEngenheiro(projeto2.getIdProjeto(), engenheiro2.getIdEngenheiro());
        projetoDAO.alocarOperario(projeto2.getIdProjeto(),operario2.getIdOperario());
        projetoDAO.consumirMaterial(projeto2.getIdProjeto(), material2.getIdMaterial());
        projetoDAO.usarEquipamento(projeto2.getIdProjeto(), equipamento2.getIdEquipamento());

        // Retirar
        projetoDAO.desalocarTodosEngenheirosDoProjeto(projeto1.getIdProjeto());
        projetoDAO.desalocarTodosOperariosDoProjeto(projeto1.getIdProjeto());
        projetoDAO.removerTodosMateriaisDoProjeto(projeto1.getIdProjeto());
        projetoDAO.removerTodosEquipamentosDoProjeto(projeto1.getIdProjeto());


        //Excluir
        projetoDAO.excluirProjeto(1);
        engenheiroDAO.excluirEngenheiro(2);
        operarioDAO.excluirOperario(operario2.getIdOperario());
        equipamentoDAO.excluirEquipamento(equipamento2.getIdEquipamento());
        materialDAO.excluirMaterial(material2.getIdMaterial());

        // Listar projetos
        List<Projeto> projetos = projetoDAO.listarProjetos();
        System.out.println("Projetos:");
        System.out.println("------------------------------------------------------------------");
        for (Projeto p : projetos) {
            System.out.println("ID: " + p.getIdProjeto());
            System.out.println("Nome: " + p.getNomeProjeto());
            System.out.println("Local: " + p.getLocal());
            System.out.println("Data de Início: " + p.getDataIncio());
            System.out.println("Data de Término: " + p.getDataTerminio());
            System.out.println();

            // Listar engenheiros alocados em cada projeto
            List<Engenheiro> engenheirosProjeto = projetoDAO.listarEngenheirosPorProjeto(p.getIdProjeto());
            System.out.println("  Engenheiros Alocados:");
            System.out.println("  -------------------------");
            for (Engenheiro e : engenheirosProjeto) {
                System.out.println("  ID: " + e.getIdEngenheiro());
                System.out.println("  Nome: " + e.getNomeEngenheiro());
                System.out.println("  Especialidade: " + e.getEspecialidade());
                System.out.println();
            }

            // Listar operários alocados em cada projeto
            List<Operario> operariosProjeto = projetoDAO.listarOperariosPorProjeto(p.getIdProjeto());
            System.out.println("  Operários Alocados:");
            System.out.println("  -------------------------");
            for (Operario o : operariosProjeto) {
                System.out.println("  ID: " + o.getIdOperario());
                System.out.println("  Nome: " + o.getNomeOperario());
                System.out.println("  Função: " + o.getFuncao());
                System.out.println();
            }

            // Listar equipamentos utilizados em cada projeto
            List<Equipamento> equipamentosProjeto = projetoDAO.listarEquipamentosPorProjeto(p.getIdProjeto());
            System.out.println("  Equipamentos Utilizados:");
            System.out.println("  -------------------------");
            for (Equipamento e : equipamentosProjeto) {
                System.out.println("  ID: " + e.getIdEquipamento());
                System.out.println("  Nome: " + e.getNomeEquipamento());
                System.out.println("  Tipo: " + e.getTipo());
                System.out.println();
            }

            // Listar materiais utilizados em cada projeto
            List<Material> materiaisProjeto = projetoDAO.listarMateriaisPorProjeto(p.getIdProjeto());
            System.out.println("  Materiais Utilizados:");
            System.out.println("  -------------------------");
            for (Material m : materiaisProjeto) {
                System.out.println("  ID: " + m.getIdMaterial());
                System.out.println("  Nome: " + m.getNomeMaterial());
                System.out.println("  Quantidade: " + m.getQuantidade());
                System.out.println();
            }

            System.out.println("------------------------------------------------------------------");
        }


    } catch (SQLException e) {
        e.printStackTrace();
    }


    }
}
