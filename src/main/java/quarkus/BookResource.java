package quarkus;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import java.util.List;

@Path("/books")
public class BookResource {

    @Inject
    private BookRepository repository;

    @GET
    public List<Book> index() {
        return repository.listAll();
    }


    @POST
    @Transactional
    public Book insert(Book book) {

        repository.persist(book);

        return book;
    }


}
