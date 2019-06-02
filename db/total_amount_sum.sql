select sum(persons_has_taxable_items.quantity * taxable_items.price) as total_amount from taxable_items 
left join persons_has_taxable_items
on taxable_items.id_item = persons_has_taxable_items.id_item 
left join persons
on persons_has_taxable_items.id_person = persons.id_person
# where taxable_items.id_item in (select distinct taxable_items.id_item from taxable_items) 

where persons.id_person = 2
