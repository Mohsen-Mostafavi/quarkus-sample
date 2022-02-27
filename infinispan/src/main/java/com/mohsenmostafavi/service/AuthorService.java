package com.mohsenmostafavi.service;

import com.mohsenmostafavi.entity.Author;
import com.mohsenmostafavi.entity.DTO.AuthorDTO;
import io.quarkus.infinispan.client.Remote;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Set;

@RequestScoped
public class AuthorService {
    @Inject
    @Remote("author")
    RemoteCache<String, Author> remoteCache;

    @Inject
    RemoteCacheManager cacheManager;

    public Set<String> getCacheNames(){
        return cacheManager.getCacheNames();
    }

    public void createAuthor(AuthorDTO authorDTO) throws Exception {
        try {
            Author author = new Author(authorDTO.getId(), authorDTO.getName(), authorDTO.getSurname());
            remoteCache.put(author.getId(),author);
        }catch (Exception e){
            throw new Exception();
        }
    }

    public Author getAuthorById(String id) {
        return remoteCache.get(id);
    }

}
