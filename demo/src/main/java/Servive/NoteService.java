package Servive;

import com.example.java.Note;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NoteService {
    private final Map<Long, Note> notes = new ConcurrentHashMap<>();
    private long currentId = 1;

    public List<Note> listAll() {
        return new ArrayList<>(notes.values());
    }

    public Note add(Note note) {
        note.setId(currentId);
        notes.put(currentId, note);
        currentId++;
        return note;
    }

    public void deleteById(long id) {
        if (notes.containsKey(id)) {
            notes.remove(id);
        } else {
            throw new RuntimeException("Note not found with id: " + id);
        }
    }

    public void update(Note note) {
        if (notes.containsKey(note.getId())) {
            notes.put(note.getId(), note);
        } else {
            throw new RuntimeException("Note not found with id: " + note.getId());
        }
    }

    public Note getById(long id) {
        if (notes.containsKey(id)) {
            return notes.get(id);
        } else {
            throw new RuntimeException("Note not found with id: " + id);
        }
    }
}
