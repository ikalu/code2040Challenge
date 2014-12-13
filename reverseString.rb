require 'HTTParty'

class ReverseString

  def get_string
    string = HTTParty.post( "http://challenge.code2040.org/api/getstring", 
                           :body => { "token"=> "8EBDqKCWgK"}.to_json, 
                           :headers => { 'Content-Type' => 'application/json' } )

    string["result"]
  end

  def reverse_the_string
    word = get_string
    print(word)
    word.to_s.reverse!
  end

  def validate_string
    result = HTTParty.post( "http://challenge.code2040.org/api/validatestring",
                                   :body => { "token" => "8EBDqKCWgK", "string" => reverse_the_string }.to_json,
                                   :headers => { 'Content-Type' => 'application/json' } )
    print(result)
  end

  def print(someThing)
    puts someThing
  end
end

result = ReverseString.new
result.validate_string
