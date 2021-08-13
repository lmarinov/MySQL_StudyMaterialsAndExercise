package exam.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class TownSeedDto {

    @XmlElement
    private String name;
    @XmlElement
    private Integer population;
    @XmlElement(name = "travel-guide")
    private String travelGuide;

    public TownSeedDto() {
    }

    @NotBlank
    @Size(min = 2)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Positive
    public Integer getPopulation() {
        return this.population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @NotBlank
    @Size(min = 10)
    public String getTravelGuide() {
        return this.travelGuide;
    }

    public void setTravelGuide(String travelGuide) {
        this.travelGuide = travelGuide;
    }
}
