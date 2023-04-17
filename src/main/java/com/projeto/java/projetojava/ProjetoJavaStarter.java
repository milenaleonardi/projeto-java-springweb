package com.projeto.java.projetojava;

import com.projeto.java.projetojava.core.model.Cidade;
import com.projeto.java.projetojava.core.model.CidadeRespository;
import com.projeto.java.projetojava.rh.model.Departamento;
import com.projeto.java.projetojava.rh.model.DepartamentoRepository;
import com.projeto.java.projetojava.rh.model.Pessoa;
import com.projeto.java.projetojava.rh.model.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;


@Component
@Transactional
public class ProjetoJavaStarter implements CommandLineRunner {

    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private CidadeRespository cidadeRespository;
    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public void run(String... args) throws Exception {
        Cidade cidade1 = new Cidade("Paranavai", "PR");
        Cidade cidade2 = new Cidade("Maringa", "PR");

        cidadeRespository.save(cidade1);
        cidadeRespository.save(cidade2);
        cidadeRespository.flush();

        Departamento departamento1 = new Departamento("Tecnologia da Informacao", "TI");
        Departamento departamento2 = new Departamento("Tecnologia de Alimentos", "TA");
        Departamento departamento3 = new Departamento("Recursos Humanos", "RH");

        departamentoRepository.save(departamento1);
        departamentoRepository.save(departamento2);
        departamentoRepository.save(departamento3);
        departamentoRepository.flush();

        Pessoa pessoa1 = new Pessoa("Joao", LocalDate.of(1990, 11, 14), "joao@email.com", "45 75648-8373", cidade1, departamento1);
        Pessoa pessoa2 = new Pessoa("Maria", LocalDate.of(1997, 05, 29), "maria@email.com", "74 83794-8371", cidade2, departamento2);
        Pessoa pessoa3 = new Pessoa("Joaquim", LocalDate.of(2000, 06, 02), "joaquim@email.com", "45 73483-8373", cidade1, departamento3);

        pessoaRepository.save(pessoa1);
        pessoaRepository.save(pessoa2);
        pessoaRepository.save(pessoa3);
    }
}
