package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
	"unicode"
)

func main() {
	//fmt.Println("Hello World!")
	part1()
}

func part1() {
	/*
	Example input:
	1abc2
	pqr3stu8vwx
	a1b2c3d4e5f
	treb7uchet
	Result:
	12, 38, 15, 77
	total: 
	142
	*/
	var total int = 0
	var line_number strings.Builder

	file, error := os.Open("input_part1.txt")
	if error != nil {
		fmt.Println("Error opening file:", error)
		return
	}
	defer file.Close()
	
	scanner := bufio.NewScanner(file)

	for scanner.Scan() {
		line := scanner.Text()
		for i:=0; i < len(line); i++ {
			if unicode.IsDigit(rune(line[i])) {
			line_number.WriteRune(rune(line[i]))
			break
			}
		}
		for i:=len(line)-1; i > 0; i-- {
			if unicode.IsDigit(rune(line[i])) {
			line_number.WriteRune(rune(line[i]))
			break
			}
		}
		number, _ := strconv.Atoi(line_number.String())
		total = total + number
	}
	fmt.Println("string number: " + line_number.String())
	fmt.Printf("total: %v\n", total)
}
