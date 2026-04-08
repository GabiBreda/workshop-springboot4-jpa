CREATE TABLE IF NOT EXISTS public.tb_payment
(
    moment timestamp(6) with time zone,
                            order_id bigint NOT NULL,
                            CONSTRAINT tb_payment_pkey PRIMARY KEY (order_id),
    CONSTRAINT fkokaf4il2cwit4h780c25dv04r FOREIGN KEY (order_id)
    REFERENCES public.tb_order (id) MATCH SIMPLE
                        ON UPDATE NO ACTION
                        ON DELETE NO ACTION
    );