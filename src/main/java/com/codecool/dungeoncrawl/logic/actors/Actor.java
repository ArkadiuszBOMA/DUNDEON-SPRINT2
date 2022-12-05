package com.codecool.dungeoncrawl.logic.actors;
import com.codecool.dungeoncrawl.utils.SoundEffect;
import com.codecool.dungeoncrawl.logic.Cell;
import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.Drawable;
import com.codecool.dungeoncrawl.logic.items.*;

public abstract class Actor implements Drawable {
    private String name;
    private Cell cell;
    protected int health = 10;
    protected int power = 5;
    protected int shield = 0;

    protected int helmet = 0;
    private boolean key = false;
    private final int[] inventory = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    //(0-health, 1-sword, 2-shield, 3-helmet, 4-heart, 5-potion, 6-diamond, 7key)

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void setHealth(int health) {
        this.health = health;
    }
    public void setPower(int power) {
        this.power = power;
    }
    public void setShield(int shield) {
        this.shield = shield;
    }
    public void setHelmet(int helmet) {
        this.helmet = helmet;
    }

    public void setName(String name) {this.name = name;}
    public String getName() {return name;}

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if (nextCell.getType().equals(CellType.EXIT) && isKey()) {
            cell.setActor(null);
            System.out.println("Level Completed");
            cell = nextCell;
        } else if (nextCell.getType().equals(CellType.DOOR) && isKey()) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.isItem() && nextCell.isAvailable() || nextCell.getType().equals(CellType.FLOOR) ) {
            if(nextCell.getType().equals(CellType.POTION)) {
                SoundEffect drink = new SoundEffect();
                drink.soundPlay(SoundEffect.getDrink());
            }
            takeItem(nextCell.getItem());
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }

    @Override
    public String toString() {return name;}

    public int getX() {
        return cell.getX();
    }
    public int getY() {
        return cell.getY();
    }

    public Cell getCell() {
        return cell;
    }
    public int getHealth() {
        return health;
    }
    public int getPower() {return power;}
    public int getShield() {return shield;}
    public int getHelmet() {return helmet;}
    public boolean isKey() {return key;}
    public int[] getInventory() {return inventory;}

    //(0-health, 1-sword, 2-shield, 3-helmet, 4-heart, 5-potion, 6-diamond, 7key)
    public void takeItem(Item item) {
        if (item instanceof Diamond) {
            if(health == 0 ) {
                inventory[0] += ((Diamond) item).getDiamond();
            } else if (((Diamond) item).getDiamond() > health) {
                inventory[0]+=((Diamond) item).getDiamond() - health;
            }
            health += ((Diamond) item).getDiamond();
            ((Diamond) item).setDiamond(0);
            item.getCell().setType(CellType.FLOOR);

        } else if (item instanceof Heart) {
            if(health == 0 ) {
                inventory[0] += ((Heart) item).getHeart();
            } else if (((Heart) item).getHeart() > health) {
                inventory[0]+=((Heart) item).getHeart() - health;
            }
            health += ((Heart) item).getHeart();
            ((Heart) item).setHeart(0);
            item.getCell().setType(CellType.FLOOR);

        } else if (item instanceof Potion) {
            health += ((Potion) item).getHealer();
            inventory[0] += ((Potion) item).getHealer();
            ((Potion) item).setHealer(0);
            item.getCell().setType(CellType.FLOOR);

        } else if (item instanceof Sword) {
            if(inventory[1] == 0 ) {
                inventory[1]+=((Sword) item).getSword();
                ((Sword) item).setSword(0);
                item.getCell().setType(CellType.FLOOR);
            }

        } else if (item instanceof Shield) {
            if(inventory[2] == 0 ) {
                inventory[2] += ((Shield) item).getShield();
                ((Shield) item).setShield(0);
                item.getCell().setType(CellType.FLOOR);
            }

        } else if (item instanceof Helmet) {
            if(inventory[3] == 0 ) {
                inventory[3]+=((Helmet) item).getHelmet();
                ((Helmet) item).setHelmet(0);
                item.getCell().setType(CellType.FLOOR);
            }

        } else if (item instanceof Key && !((Key) item).isTaken()) {
            inventory[7]+=1;
            key = true;
            ((Key) item).setTaken(true);
            item.getCell().setType(CellType.FLOOR);
        }
    }
        //metoda walki z
    public void attackPlayer(Actor actor, Cell cell) {
        System.out.println("Atakuje " + this.getTileName());
        System.out.println("Broni się " + actor.getTileName());
        actor.setHealth(actor.getHealth() - this.power + actor.getShield() + actor.getHelmet());
        this.health = this.health - actor.power + this.shield + this.helmet;
        if (actor.getHealth() <= 0) {
            cell.setType(CellType.FLOOR);
            SoundEffect hit = new SoundEffect();
            hit.soundPlay(SoundEffect.getHit());
            System.out.println("Broni się " + actor.getTileName() + " ma tyle żyć " + actor.health + " " + actor.power);
            System.out.println("Atakuje " + this.getTileName() + " ma tyle żyć " + this.health + " " + this.power);
        } else if (this.health <= 0) {
            cell.setActor(null);
            SoundEffect hit = new SoundEffect();
            hit.soundPlay(SoundEffect.getHit());
            System.out.println("Broni się " + actor.getTileName() + " ma tyle żyć " + actor.health + " " + actor.power);
            System.out.println("Atakuje " + this.getTileName() + " ma tyle żyć " + this.health + " " + this.power);
        }
    }
}
