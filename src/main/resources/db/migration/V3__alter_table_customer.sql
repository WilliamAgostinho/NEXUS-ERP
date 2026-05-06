ALTER TABLE customer
    ADD CONSTRAINT uq_customer_document UNIQUE (document);