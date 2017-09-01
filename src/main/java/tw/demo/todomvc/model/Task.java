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
    private int status;

    @Column(nullable = false)
    private int available;

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
        map.put("status", this.status);
        return map;
    }
}
