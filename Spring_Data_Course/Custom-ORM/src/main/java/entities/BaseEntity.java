package entities;

import customerOrmFramework.annotations.Id;

public class BaseEntity {
    @Id
    private Long id;

    public BaseEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
