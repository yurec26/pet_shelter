module backend {
    requires com.google.gson;
    requires json.simple;
    requires com.fasterxml.jackson.datatype.jsr310;
    requires com.fasterxml.jackson.databind;

    exports org.example.backend.entity;
    exports org.example.backend.constants;
    exports org.example.backend.util;
    exports org.example.backend.service;
    exports org.example.backend.repository;

    opens org.example.backend.service to com.google.gson, com.fasterxml.jackson.databind;
    opens org.example.backend.entity to com.google.gson, com.fasterxml.jackson.databind;
    opens org.example.backend.util to com.google.gson, com.fasterxml.jackson.databind;
    opens org.example.backend.repository to com.google.gson, com.fasterxml.jackson.databind;


}