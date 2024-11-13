package clueGame;

public class Solution {
    public String person;
    public String weapon;
    public String room;

    public Solution(String person, String weapon, String room) {
        this.person = person;
        this.weapon = weapon;
        this.room = room;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Solution solution = (Solution) obj;
        return person.equals(solution.person) &&
               weapon.equals(solution.weapon) &&
               room.equals(solution.room);
    }

    @Override
    public int hashCode() {
        return person.hashCode() + weapon.hashCode() + room.hashCode();
    }

    @Override
    public String toString() {
        return "Solution: " + person + ", " + weapon + ", " + room;
    }
}