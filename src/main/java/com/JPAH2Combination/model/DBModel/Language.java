package com.JPAH2Combination.model.DBModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Language")
public class Language {

    public String getLanguageId() {
        return languageId;
    }

    public void setLanguageId(String languageId) {
        this.languageId = languageId;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    @Id
    @Column(name = "LanguageID")
    String languageId;

    @Column(name = "LANGUAGENAME")
    String languageName;

    public Language(){}

    @Override
    public String toString() {
        return "Language{" +
                "languageId='" + languageId + '\'' +
                ", languageName='" + languageName + '\'' +
                '}';
    }
}
