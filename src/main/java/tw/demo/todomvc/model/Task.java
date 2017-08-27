package tw.demo.todomvc.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private int completed;

    @Column(nullable = false)
    private int available;

    public Task() {
    }

    public Task(String text, int completed) {
        this.completed = completed;
        this.text = text;
    }

    public Task(String text, int completed, int available) {
        this.completed = completed;
        this.text = text;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public Map formatTask() {
        Map map = new HashMap();
        map.put("text", this.text);
        map.put("id", this.id);
        map.put("completed", this.completed);
        return map;
    }
}
