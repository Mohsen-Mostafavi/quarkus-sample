package com.mohsenmostafavi.config;

import com.mohsenmostafavi.entity.Author;
import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(includeClasses = {Author.class}, schemaPackageName = "author_sample")
public interface AuthorContextInitializer extends SerializationContextInitializer {
}
