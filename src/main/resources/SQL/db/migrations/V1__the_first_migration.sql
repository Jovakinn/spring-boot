CREATE UNIQUE INDEX idx_article ON items(article);

INSERT INTO items (article, init_price, name, price) VALUES ('test01', 15000, 'test name', 15000) ON CONFLICT DO NOTHING;