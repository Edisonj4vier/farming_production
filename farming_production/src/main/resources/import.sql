INSERT INTO `tbl_products`(`amount`, `category`, `description`, `name`) VALUES (2,'legumbre','semilllas','acelga')

INSERT INTO `tbl_maintenance`(`id`, `amount`, `date`, `description`, `name`, `state`, `product_id`) VALUES (1,100,'2002/12/12','Arreglo','ló','activo','1')
INSERT INTO `tbl_maintenance`(`id`, `amount`, `date`, `description`, `name`, `state`, `product_id`) VALUES (2,100,'2002/12/12','Arreglo','Papa','activo','1')
INSERT INTO `tbl_maintenance`(`id`, `amount`, `date`, `description`, `name`, `state`, `product_id`) VALUES (3,100,'2002/12/12','Arreglo','Zanahoria','activo','1')

INSERT INTO `tbl_supplies`(`id`, `amount`, `description`, `expericion_date`, `name`, `maintenance_id`) VALUES (1,100,'Fertilizante','2002/12/01','Fertilizante','2')
