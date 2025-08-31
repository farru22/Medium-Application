package com.farfor.journalApp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.farfor.journalApp.entity.UserEntry;

//This is the repository which will directly run queries on 
public class SentimentAnalyser {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<UserEntry> getUserByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        query.addCriteria(Criteria.where("sentimentAnalyser").is(true));
        List<UserEntry> user= mongoTemplate.find(query,UserEntry.class);
        return user;
    }

}

// 1. Query Method DSL

// You just write method names in your repository interface.

// Spring Data reads the method name and generates the query automatically.

// Example:

// UserEntry findByUserName(String userName);
// List<UserEntry> findByAgeGreaterThan(int age);

// 👉 Very quick, simple, and readable.
// ❌ But: limited when queries become complex (like multiple conditions, OR,
// nested fields).

// 🔹 2. Query Criteria (using Criteria + Query)

// You write queries programmatically with Criteria and Query classes (from
// Spring Data MongoDB).

// Example:

// Query query = new Query();
// query.addCriteria(Criteria.where("userName").is("farhan"));
// UserEntry user = mongoTemplate.findOne(query, UserEntry.class);

// 👉 Very powerful, flexible, works for complex queries (AND, OR, ranges,
// nested documents).
// ❌ But: more code, less readable for simple cases.

// 🔹 Which is better?

// Use Query Method DSL → when queries are simple (like findByUserName,
// deleteByEmail, findByAgeGreaterThan).

// Use Query Criteria → when queries are complex (like multiple conditions,
// dynamic queries, or advanced MongoDB operators).

// 🔹 Can we replace DSL with Criteria?

// ✅ Yes, everything you do with Query Method DSL can also be done with Query
// Criteria, because Criteria is lower-level and more powerful.
// 👉 But the opposite is not true — some complex Criteria queries cannot be
// expressed easily with DSL.

// ✅ In short:

// DSL = easy, quick, readable (good for 80% use cases).

// Criteria = flexible, powerful, but more code (good for advanced queries).

// What is MongoTemplate?

// MongoTemplate is a core class in Spring Data MongoDB.

// It’s like a helper tool that lets you interact with MongoDB directly.

// You use it when you need more control than what MongoRepository and Query
// Method DSL provide.

// 🔹 What can it do?

// With MongoTemplate, you can:

// Insert, update, delete, and find documents.

// Run custom queries using Query and Criteria.

// Work with aggregations (grouping, projections, etc).

// Handle more complex MongoDB operations that repositories can’t express
// easily.

// When to use MongoTemplate?

// If your query is simple → Repository / Query DSL is enough.

// If your query is complex (multiple conditions, OR, nested docs, aggregations)
// → MongoTemplate is better.

// ✅ In short:

// MongoRepository = easy, quick CRUD + simple queries.

// MongoTemplate = flexible, powerful custom queries.