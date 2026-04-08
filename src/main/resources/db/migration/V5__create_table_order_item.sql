CREATE TABLE IF NOT EXISTS public.tb_order_item
(
    price double precision,
    quantity integer,
    product_id bigint NOT NULL,
    order_id bigint NOT NULL,
    CONSTRAINT tb_order_item_pkey PRIMARY KEY (order_id, product_id),
    CONSTRAINT fk4h5xid5qehset7qwe5l9c997x FOREIGN KEY (product_id)
    REFERENCES public.tb_product (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkgeobgl2xu916he8vhljktwxnx FOREIGN KEY (order_id)
    REFERENCES public.tb_order (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
);