package com.levelsbeyond;

import com.levelsbeyond.core.NoteEntity;
import com.levelsbeyond.db.HibernateNoteDAO;
import com.levelsbeyond.db.NoteDAO;
import com.levelsbeyond.resources.NoteResource;
import com.levelsbeyond.services.NoteService;
import com.levelsbeyond.services.NoteServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.hibernate.SessionFactory;

public class NotesApplication extends Application<NotesConfiguration> {

    public static void main(final String[] args) throws Exception {
        new NotesApplication().run(args);
    }

    @Override
    public String getName() {
        return "Notes";
    }

    private final HibernateBundle<NotesConfiguration> hibernate = new HibernateBundle<NotesConfiguration>(NoteEntity.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(NotesConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(final Bootstrap<NotesConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(hibernate);
        bootstrap.addBundle(new MigrationsBundle<NotesConfiguration >() {
            @Override
            public DataSourceFactory getDataSourceFactory(NotesConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
    }

    @Override
    public void run(final NotesConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        NoteDAO noteDAO = new HibernateNoteDAO(sessionFactory);
        NoteService noteService = new NoteServiceImpl(noteDAO);
        environment.jersey().register(new NoteResource(noteService));
    }

}