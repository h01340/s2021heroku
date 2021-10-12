package s2021.bookstoreAndMariaDB.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import s2021.bookstoreAndMariaDB.domain.Book;
import s2021.bookstoreAndMariaDB.domain.BookRepository;
import s2021.bookstoreAndMariaDB.domain.CategoryRepository;



@Controller
public class BookController {
	// injektoi repositoryt interface BookController-luokalle
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	
    @RequestMapping(value= {"/booklist"})
    public String bookList(Model model) {	
       model.addAttribute("books", bookRepository.findAll());
       return "bookList";
    }

	@GetMapping("addBook")
	public String newBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", categoryRepository.findAll());
		return "/newBook";
	}
	
	@PostMapping("saveBook")
	public String saveBook(Book book, Model model) {
		System.out.println("saveBook method: " + book);
		bookRepository.save(book);
		
		return "redirect:/booklist";
	}
	
	@GetMapping("delete/{id}")
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		bookRepository.deleteById(id);
		return "redirect:/booklist";
	}
	
	
	@GetMapping("edit/{id}")
	public String editBook(@PathVariable("id") Long id, Model model) {
		//search book, findById
		model.addAttribute("book", bookRepository.findById(id));
		model.addAttribute("categories", categoryRepository.findAll());
		return "editBook";
	}
}
