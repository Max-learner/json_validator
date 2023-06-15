package org.example.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

public class JsonValidator {

    public Set<ValidationMessage> validateJsonToSchema(String filePath, String schemaPath) {
        Set<ValidationMessage> validationMessages = new HashSet<>();
        try {
            InputStream jsonStream = new FileInputStream(filePath);
            InputStream schemaStream = new FileInputStream(schemaPath);

            JsonNode json = new ObjectMapper().readTree(jsonStream);
            JsonSchema schema = JsonSchemaFactory
                    .getInstance(SpecVersion.VersionFlag.V6)
                    .getSchema(schemaStream);
            validationMessages = schema.validate(json);
        } catch (Exception e) {
            e.printStackTrace();
            validationMessages.add(new ValidationMessage
                    .Builder()
                    .customMessage("ERROR: Input stream run with exception: " + e.getMessage())
                    .build());
        }
        return validationMessages;
    }
}
