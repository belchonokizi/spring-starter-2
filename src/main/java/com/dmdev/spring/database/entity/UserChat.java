package com.dmdev.spring.database.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

/**тк с маппингом many to many много проблем, то создается отдельная сущность
для таблицы users_chat
 **/
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users_chat")
public class UserChat implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ManyToMany реализовали через ManyToOne в этом классе
     * и OneToMany в классах Chat и User
     * **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    @ToString.Exclude
    private Chat chat;

    public void setUser(User user) {
        this.user = user;
        this.user.getUserChats().add(this);
    }

    public void setChat(Chat chat) {
        this.chat = chat;
        this.chat.getUserChats().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserChat userChat = (UserChat) o;
        return getId() != null && Objects.equals(getId(), userChat.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
