
package com.oskar.kow.ActionsTrackerServer.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Oskar Kowalewski
 */
@Entity
@EqualsAndHashCode(of = "id")
@Table(name = "programs")
public class Program {
    
    @Getter
    @Id
    UUID id = UUID.randomUUID();
    
    @Getter
    @Setter
    @Column(nullable=false)
    String name;
    
    @OneToMany(mappedBy = "program")
    @Getter
    List<Broadcast> broadcasts = new ArrayList<>();
    
}
