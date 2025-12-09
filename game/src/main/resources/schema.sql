CREATE TABLE IF NOT EXISTS public.users (
    id UUID PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    authorities VARCHAR(100) NOT NULL,
    points INTEGER NOT NULL DEFAULT 1000
);

CREATE TABLE IF NOT EXISTS public.horses (
    id UUID PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    odds DECIMAL(5,2) NOT NULL,
    wins INTEGER DEFAULT 0,
    races INTEGER DEFAULT 0
);

CREATE TABLE IF NOT EXISTS public.races (
    id UUID PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'OPEN',
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    winner_horse_id UUID REFERENCES horses(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS public.race_horses (
    id UUID PRIMARY KEY,
    race_id UUID NOT NULL REFERENCES races(id),
    horse_id UUID NOT NULL REFERENCES horses(id),
    position INTEGER,
    UNIQUE(race_id, horse_id)
);

CREATE TABLE IF NOT EXISTS public.bets (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL REFERENCES users(id),
    race_id UUID NOT NULL REFERENCES races(id),
    horse_id UUID NOT NULL REFERENCES horses(id),
    amount INTEGER NOT NULL,
    potential_payout DECIMAL(10,2),
    actual_payout DECIMAL(10,2) DEFAULT 0,
    status VARCHAR(50) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO horses (id, name, odds) VALUES
    (gen_random_uuid(), 'Thunder Bolt', 2.5),
    (gen_random_uuid(), 'Lightning Strike', 3.2),
    (gen_random_uuid(), 'Storm Runner', 4.1),
    (gen_random_uuid(), 'Wind Chaser', 5.0),
    (gen_random_uuid(), 'Fire Spirit', 6.5),
    (gen_random_uuid(), 'Ocean Wave', 7.2)
ON CONFLICT DO NOTHING;
