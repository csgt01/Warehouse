package de.csgt.domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;

@Entity
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	private Calendar createdAt;
    
	private String name;
    private String description;
    private String imageUrl;
    
    @Min(0)
    private Integer available = 0;
    
    @OneToMany
    private List<Buy> buys;
    
    public Material() {
		super();
	}

    public Material(Integer id, String name, String description, String imageUrl, Integer available) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.name = name;
		this.description = description;
		this.imageUrl = imageUrl;
		this.available = available;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public List<Buy> getBuys() {
		return buys;
	}

	public void setBuys(List<Buy> buys) {
		this.buys = buys;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"createdAt\":\"" + createdAt + "\", \"name\":\"" + name + "\", \"description\":\"" + description + "\", \"imageUrl\":\"" + imageUrl + "\", \"buys\":\"" + buys + "\"}";
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}
	
}