package com.projeto.java.projetojava.rh.controller;

import com.projeto.java.projetojava.core.model.CidadeRespository;
import com.projeto.java.projetojava.rh.model.Departamento;
import com.projeto.java.projetojava.rh.model.DepartamentoRepository;
import com.projeto.java.projetojava.rh.model.Pessoa;
import com.projeto.java.projetojava.rh.model.PessoaRepository;
import com.projeto.java.projetojava.rh.model.dtos.AutoCompleteDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class PessoaController {

    private PessoaRepository pessoaRepository;
    private CidadeRespository cidadeRespository;
    private DepartamentoRepository departamentoRepository;

    private List<Departamento> departamentosSugeridos = new ArrayList<>();

    public PessoaController(PessoaRepository pessoaRepository, CidadeRespository cidadeRespository, DepartamentoRepository departamentoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.cidadeRespository = cidadeRespository;
        this.departamentoRepository = departamentoRepository;
    }

    @RequestMapping("/rh/pessoas/departamentosNomeAutoComplete")
    @ResponseBody
    public List<AutoCompleteDTO> departamentosNomeAutoComplete(@RequestParam("term") String term) {
        List<AutoCompleteDTO> sugestoes = new ArrayList<>();
        try {
            if(term.length() == 3) {
                departamentosSugeridos = departamentoRepository.search(term);
            }
            for(Departamento departamento:departamentosSugeridos) {
                if(departamento.getNome().toLowerCase().contains(term.toLowerCase())){
                    AutoCompleteDTO dto = new AutoCompleteDTO(departamento.getNome(), Long.toString(departamento.getId()));
                    sugestoes.add(dto);
                }
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return sugestoes;
    }

    @GetMapping("/rh/pessoas")
    public String pessoas(Model model) {
        model.addAttribute("listaPessoas", pessoaRepository.findAll());
        return "/rh/pessoas/index";
    }

    @GetMapping("/rh/pessoas/nova")
    public String novaPessoa(Model model){
        model.addAttribute("pessoa", new Pessoa());
        model.addAttribute("cidade", cidadeRespository.findAll());
        return "rh/pessoas/form";
    }
//    @GetMapping("/rh/pessoas/nova")
//    public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa){
//        return "rh/pessoas/form";
//    }

    @GetMapping("/rh/pessoas/{id}")
    public String alterarPessoa(@PathVariable("id") long id, Model model) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
        if(!pessoaOpt.isPresent()) {
            throw new IllegalArgumentException("Pessoa inválida.");
        }
        model.addAttribute("pessoa", pessoaOpt.get());
        model.addAttribute("cidade", cidadeRespository.findAll());
        return "rh/pessoas/form";
    }

    @PostMapping("/rh/pessoas/salvar")
    public String salvarPessoa(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()) {
            model.addAttribute("cidade", cidadeRespository.findAll());
            return "/rh/pessoas/form";
        }

        pessoaRepository.save(pessoa);
        return "redirect:/rh/pessoas";
    }

    @GetMapping("/rh/pessoas/excluir/{id}")
    public String excluirPessoa(@PathVariable("id") long id) {
        Optional<Pessoa> pessoaOpt = pessoaRepository.findById(id);
        if(!pessoaOpt.isPresent()) {
            throw new IllegalArgumentException("Pessoa inválida.");
        }
        pessoaRepository.delete(pessoaOpt.get());
        return "redirect:/rh/pessoas";
    }


}
