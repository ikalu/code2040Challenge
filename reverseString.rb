require 'HTTParty'

class ReverseString

  def getString
    string = HTTParty.post( "http://challenge.code2040.org/api/getstring", 
                           :body => { "token"=> "8EBDqKCWgK"}.to_json, 
                           :headers => { 'Content-Type' => 'application/json' } )

    string["result"]
  end

  def reverseTheString
    word = getString
    print(word)
    word.to_s.reverse!
  end

  def validateString
    reversedString = HTTParty.post( "http://challenge.code2040.org/api/validatestring",
                                   :body => { "token" => "8EBDqKCWgK", "string" => reverseTheString }.to_json,
                                   :headers => { 'Content-Type' => 'application/json' } )
    reversedString
  end

  def print(someThing)
    puts someThing
#    puts reverseTheString
#    puts validateString
  end

end


reverseString = ReverseString.new

#puts reverseString.getString
puts reverseString.reverseTheString
#puts reverseString.validateString
#reverseString.print
