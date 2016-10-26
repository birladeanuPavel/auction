package com.birladeanu.dal.model.interceptor;

import com.birladeanu.dal.model.Item;
import com.birladeanu.dal.model.ItemAudit;
import lombok.NonNull;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Session;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by pavel on 10/24/16.
 */
public class ItemAuditInterceptor extends EmptyInterceptor{

    private static final Logger LOGGER = LogManager.getLogger();

    @Setter
    @NonNull
    private Session currentSession;

    @SuppressWarnings("unchecked")
    @Override
    public void postFlush(Iterator entities) {
        if (currentSession != null) {
            Session temporarySession = currentSession.sessionWithOptions()
                    .transactionContext()
                    .connection()
                    .openSession();

            Stream<Object> entitiesStream = StreamSupport
                    .stream(Spliterators.spliteratorUnknownSize(entities, Spliterator.ORDERED), false);

            entitiesStream.forEach(o -> {
                        if (o instanceof Item) {
                            Item item = (Item) o;
                            try {
                                temporarySession.save(new ItemAudit(item.getId(), item.getDescription()));
                            } finally {
                                temporarySession.flush();
                            }
                            LOGGER.debug("Item flushed: " + item);
                        }
                    }
            );
        }

    }

}
