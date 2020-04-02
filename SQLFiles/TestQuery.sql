-- This sql file is for debugging purpose
Create Or Replace View AVG_NUM_POKEMON_ON_REGION AS
    Select avg(counting) average_num
    From (Select count(*) counting
        From (Select *
            From Pokemon p
            Left Join Found_In f
            On p.pokemon_id = f.pokemon_id) pf
        Group by pf.region);


CREATE OR REPLACE VIEW POKEMON_IN_ALL_REGION AS
Select p.name
From pokemon p
Where Not Exists (
    (Select region From area)
Minus
    (Select f.region
    From Found_In f
    Where p.pokemon_id = f.pokemon_id));


