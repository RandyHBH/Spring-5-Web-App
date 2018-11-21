package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitializationData implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public InitializationData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData() {
        Author randy = new Author("Randy Hector", "Bartumeu Huergo");
        Book cleanCode = new Book("Clean Code", "12345", "Amazon");
        randy.getBooks().add(cleanCode);
        cleanCode.getAuthors().add(randy);

        authorRepository.save(randy);
        bookRepository.save(cleanCode);

        Author robert = new Author("Robert", "Guevara");
        Book cleanCodeInNodeJS = new Book("Clean Code in NodeJS", "67890", "Amazon");
        robert.getBooks().add(cleanCodeInNodeJS);
        cleanCodeInNodeJS.getAuthors().add(robert);

        authorRepository.save(robert);
        bookRepository.save(cleanCodeInNodeJS);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
