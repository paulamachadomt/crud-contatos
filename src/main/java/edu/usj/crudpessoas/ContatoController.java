package edu.usj.crudpessoas;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class ContatoController {
    
    @Autowired
    ContatoRepository contatoRepository;

    @GetMapping(value="/")
    public ModelAndView getListaContatos() {
        List<Contato> listaContatos = contatoRepository.findAll();
        
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("listaContatos", listaContatos);
        return modelAndView;
    }



    @GetMapping(value="/detalhes/{id}")
    public ModelAndView getContatoById(@PathVariable Long id) {

        //ler do banco
        Contato contato = contatoRepository.findById(id).get();       
        

        ModelAndView modelAndView = new ModelAndView("detalhes");
        modelAndView.addObject("contato", contato);
        return modelAndView;

    }



    @GetMapping(value="/cadastro")
    public ModelAndView getCadastro() {
        Contato contato = new Contato();
        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("contato", contato);
        return modelAndView;
    }


    @PostMapping(value="add")
    public ModelAndView postContato(@RequestParam String nome, @RequestParam String tipo, @RequestParam String telefone) {
        
        Contato contato = new Contato();
        contato.setNome(nome);
        contato.setTipo(tipo);
        contato.setTelefone(telefone);

        contatoRepository.save(contato);
        
        List<Contato> listaContatos = contatoRepository.findAll();

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("listaContatos", listaContatos);
        return modelAndView;

        // return "redirect:/";
    }
    



    @GetMapping(value="/deletar/{id}")
    public String deletarContatoById(@PathVariable Long id) {
        contatoRepository.deleteById(id);     
        
        return "redirect:/";  
    }




    @GetMapping(value="/editar/{id}")
    public ModelAndView editarContatoById(@PathVariable Long id) {
        Contato contato = contatoRepository.findById(id).get();

        ModelAndView modelAndView = new ModelAndView("cadastro");
        modelAndView.addObject("contato", contato);
        return modelAndView;
    }


}

    
