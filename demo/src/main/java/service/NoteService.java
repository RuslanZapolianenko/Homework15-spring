package service;
import entity.Note;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class NoteService {
    private final List<Note> notes = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Note> listAll() {
        return notes;
    }

    public Note add(Note note) {
        note.setId(idGenerator.getAndIncrement());
        notes.add(note);
        return note;
    }

    public void deleteById(long id) {
        notes.removeIf(note -> note.getId() == id);
    }

    public void update(Note note) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId().equals(note.getId())) {
                notes.set(i, note);
                return;
            }
        }
    }

    public Note getById(long id) {
        for (Note note : notes) {
            if (note.getId() == id) {
                return note;
            }
        }
        throw new RuntimeException("Note not found with id: " + id);
    }
}
