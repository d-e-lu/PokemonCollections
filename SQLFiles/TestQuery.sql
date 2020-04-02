-- This sql file is for debugging purpose
CREATE OR REPLACE VIEW view_temp AS
    Select avg(counting) average_num
    From (Select count(*) counting
        From (Select *
            From Pokemon p
            Left Join Found_In f
            On p.pokemon_id = f.pokemon_id) pf
        Group by pf.region);

SELECT * FROM view_temp;