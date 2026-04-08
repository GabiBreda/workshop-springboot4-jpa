CREATE TABLE IF NOT EXISTS public.tb_product_category
(
    product_id bigint NOT NULL,
    category_id bigint NOT NULL,
    CONSTRAINT tb_product_category_pkey PRIMARY KEY (product_id, category_id),
    CONSTRAINT fk5r4sbavb4nkd9xpl0f095qs2a FOREIGN KEY (category_id)
        REFERENCES public.tb_category (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkgbof0jclmaf8wn2alsoexxq3u FOREIGN KEY (product_id)
        REFERENCES public.tb_product (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);