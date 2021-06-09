package jtm.activity03;

/**
 * Black Knight is brave soldier who fights till he is alive. He doesn't bother
 * if some of his arms or legs are cut off. You can kill him only when he lose
 * head. More info at:
 * https://en.wikipedia.org/wiki/Black_Knight_%28Monty_Python%29
 */
public class BlackKnight {
	// Class variables which are shared between all class members (objects)
	public static short totalKnights; // total number of knights at the start of
	// the battle
	public static short aliveKnights; // total number of alive knights
	public static short deadKnights; // total number of dead knights
	public static BlackKnight[] knights; // array of knights in the battle

	// instance variables which are separate for each class member (object)
	public String name; // knight name
	public byte arms, legs, head; // number of limbs
	public boolean alive; // is knight alive

	public static void setBattle(int initialNumber) {
		knights = new BlackKnight[initialNumber];
		aliveKnights = 0;
		totalKnights = 0;
		deadKnights = 0;
		// TODO initialize array of knights with the passed size.
		// Reset total numbers of total and alive knights to zero
	}

	public BlackKnight(String name) {
		this.name = name;
		this.arms = 2;
		this.legs = 2;
		this.head = 1;
		this.alive = true;
		knights[totalKnights] = this;
		totalKnights++;
		aliveKnights++;
		// TODO set name of newly created knight
		// 1. set proper count of his arms, legs and head,
		// 2. set his status to alive
		// 3. put reference of this knight into next free cell of knights static
		// array
		// 4. increase number of total and alive knights of static counters
		// HINT: use "this.name" to access name of knight which otherwise is shadowed
		// by parameter of constructor, which is also called "name"
	}

	public String cutOffArm() {
		// TODO handle cutting off knight's arms in following way:
		if (!alive) {
			return "Only chicken beats dead!";
		} else {
			if (arms > 0) {
				arms--;
				return "Bugger!";
			} else {
				return "Haah!";
			}
		}
	}


	public String cutOffLeg() {
		if (!alive) {
			return "Only chicken beats dead!";
		} else {
			if (legs > 0) {
				legs--;
				return "Bollocks!";
			} else {
				return "Haah!";
			}
		}

		// TODO handle cutting off legs knight's legs in following way:
		// If knight is dead, return "Only chicken beats dead!"
		// If knight has some legs, cut one off and return "Bollocks!"
		// Else return just "Haah!"

	}

	public String cutOffHead() {
		if (!alive)
			return "Only chicken beats dead!";

		if (head > 0)
			head--;
			alive = false;
			aliveKnights--;
			deadKnights++;
			String names = "";
			for (BlackKnight knight : knights
			) {
				if (knight.alive) {
					names = names + knight.name + ",";
				}
				if (names.length() > 2) {
					names = names.substring(0, names.length() - 2);
				}
				if ("".equals(names)) {
					return "You'l burn in hell forever!";
				}
			}

		return "You'l never win!" + names + " will still fight!";
	}
}


