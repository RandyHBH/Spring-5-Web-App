package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitializationData implements ApplicationListener<ContextRefreshedEvent> {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public InitializationData(AuthorRepository authorRepository,
                              BookRepository bookRepository,
                              PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        Author randy = new Author("Randy Hector", "Bartumeu Huergo");
        Publisher amazon = new Publisher("Amazon", "Some Where");
        Book cleanCode = new Book("Clean Code", "12345", amazon);
        randy.getBooks().add(cleanCode);
        cleanCode.getAuthors().add(randy);

        publisherRepository.save(amazon);
        authorRepository.save(randy);
        bookRepository.save(cleanCode);

        Author robert = new Author("Robert", "Guevara");
        Publisher infoQ = new Publisher("InfoQ", "Some Where 2");
        Book cleanCodeInNodeJS = new Book("Clean Code in NodeJS", "67890", infoQ);
        robert.getBooks().add(cleanCodeInNodeJS);
        cleanCodeInNodeJS.getAuthors().add(robert);

        publisherRepository.save(infoQ);
        authorRepository.save(robert);
        bookRepository.save(cleanCodeInNodeJS);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
