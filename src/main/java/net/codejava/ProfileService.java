package net.codejava;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfileService {

    @Autowired
    private ProfileRepository repo;

    public List<Profile> listAll() {
        return repo.findAll();
    }

    public void save(Profile Profile) {
        repo.save(Profile);
    }

    public Profile get(long id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
